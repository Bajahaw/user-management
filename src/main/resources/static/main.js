const login_btn = document.getElementById('btn');
const signup_btn = document.getElementById('signup');
const logout_btn = document.getElementById('loguot');
const form = document.getElementById('userForm');

form.addEventListener('submit', (event) => {
    event.preventDefault();
    fetch('/api/v1/auth/authenticate', {
        method: 'POST', body: new FormData(form),
    })
        .then(response => response.json())
        .then(data => {
            let token = data.token;
            localStorage.setItem('jwt', token);
            home();
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
            document.open();
            document.write(html);
            document.close();
        });
}