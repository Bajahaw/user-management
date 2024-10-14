// const login_btn = document.getElementById('btn');
// const signup_btn = document.getElementById('signup');
// const logout_btn = document.getElementById('loguot');
const form = document.getElementById('userForm');
const registerForm = document.getElementById('registerForm');

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
            if (!response.ok) {
                alert(response.text);
            } else response.json()
                .then(data => {
                    let token = data.token;
                    localStorage.setItem('jwt', token);
                    // home();
                });
        });

});

if (form) form.addEventListener('submit', (event) => {
    event.preventDefault();
    fetch('/login', {
        method: 'POST',
        body: new FormData(form),
        redirect: "follow"
    })
        .then(response => {
            if (response.headers.get('Content-Type') === 'application/json') {
                response.json()
                    .then(data => {
                        let token = data.token;
                        localStorage.setItem('jwt', token);
                        // home();
                    });
            }
            else response.text()
                .then(response => {
                    document.getElementById('state').innerText = response
                });
        });
});

function home() {
    fetch('/home', {
        method: 'GET', headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt')}`, 'Content-Type': 'application/json'
        }
    })
        .then(response => response.text())
        .then(html => {
            history.pushState(null, '', '/home');
            document.open();
            document.write(html);
            document.close();
        });
}

function form_to_json(form) {
    const formData = new FormData(form);
    const data = {};
    formData.forEach((value, key) => {
        data[key] = value;
    });
    return data;
}

