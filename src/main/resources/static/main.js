// const login_btn = document.getElementById('btn');
// const signup_btn = document.getElementById('signup');
// const logout_btn = document.getElementById('loguot');
dashboard_btn = document.getElementById('dashboard');
form = document.getElementById('userForm');
registerForm = document.getElementById('registerForm');


if (registerForm) registerForm.addEventListener('submit', (event) => {
    event.preventDefault();

    fetch('/api/v1/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(form_to_json(registerForm))
    })
        .then(response => {
            if (response.headers.get('Content-Type').startsWith('application/json')) {
                response.json()
                    .then(data => {
                        let token = data.token;
                        localStorage.setItem('jwt', token);
                        home(data);
                    });
            } else response.text()
                .then(response => {
                    setAlertMessage(response);
                });
        });

});

if (form) form.addEventListener('submit', (event) => {
    event.preventDefault();
    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(form_to_json(form)),
        redirect: "follow"
    })
        .then(response => {
            if (response.headers.get('Content-Type').startsWith('application/json')) {
                response.json()
                    .then(data => {
                        let token = data.token;
                        localStorage.setItem('jwt', token);
                        home(data);
                    });
            } else response.text()
                .then(response => {
                    setAlertMessage(response);
                });
        }).catch(err => console.log(err));
});

if (dashboard_btn) dashboard_btn.addEventListener('click', async () => {
    let users = await fetch(
        '/dashboard/users', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('jwt')}`,
            }
        }).then(r => r.json())
        .catch(err => console.log(err));

    fetch('/dashboard', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt')}`,
        }
    })
        .then(r => r.text())
        .then(html => {
            history.pushState(null, '', '/dashboard');
            document.open();
            document.write(html);
            document.getElementById('list').innerHTML =
                `
                ${users.map(user => `<li id="${user.username}-item" class="user-data rounded-2 border border-info bg-info-subtle mb-1">
                    <div class=" d-flex justify-content-between align-items-center">
                        <small class="m-1">${user.username}</small>
                        <button id="${user.username}" class="btn btn-sm">🗑</button>
                    </div>
                </li>`).join(`\n`)}
            `
            users.forEach(user =>
                document
                    .getElementById(user.username)
                    .addEventListener('click', (e) => {
                        e.preventDefault();
                        fetch(`delete/${user.username}`, {
                            method: 'DELETE',
                            headers: {
                                'Authorization': `Bearer ${localStorage.getItem('jwt')}`
                            }
                        }).then(() => {
                            document.getElementById(`${user.username}-item`).remove();
                        });
                    }));
            document.close();
        })
        .catch(err => console.log(err));
});

function home(data) {
    fetch('/home', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt')}`,
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.text())
        .then(html => {
            history.pushState(null, '', `/${data.user.username}`);
            document.open();
            document.write(html);
            document.getElementById('email').innerText = data.user.username;
            document.getElementById('user').innerText = data.user.name;
            if (!data.user.authorities?.some(auth => auth.authority === 'ROLE_ADMIN'))
                document.getElementById('dashboard').style.visibility = 'hidden';
            document.close();
        }).catch(err => console.log(err));
}

function form_to_json(form) {
    const formData = new FormData(form);
    const data = {};
    formData.forEach((value, key) => {
        data[key] = value;
    });
    return data;
}

function setAlertMessage(response) {
    const message = document.getElementById('state');
    message.innerHTML = `
        <ul class="mb-0">
            ${response.split('\n').map(line => `<li>${line}</li>`).join(``)}
        </ul>
    `;
    message.style.position = 'relative';
    message.style.visibility = 'visible';
}

