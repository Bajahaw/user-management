const btn = document.getElementById('btn');
const user= document.getElementById('username');
const pass= document.getElementById('password');

const xmlhttp = new XMLHttpRequest();

function login(xmlhttp){
    xmlhttp.open("POST", "/login");
    xmlhttp.setRequestHeader('Content-Type', 'application/json');
    const credentials = JSON.stringify({username: user.value, password: pass.value});
    xmlhttp.send(credentials);
    xmlhttp.onload = function() {
        document.getElementById('state').innerHTML =
            this.response;
        if (this.status === 200){
            window.history.pushState({}, '', '/user');
            btn.innerHTML = 'LogOut'
            btn.style.background = 'red';
        }
    }
}
function logout(xmlhttp){
    xmlhttp.open("GET", "/logout");
    xmlhttp.send();
    xmlhttp.onload = function (){
        document.getElementById('state').innerHTML =
            this.response;
        if (this.status === 200){
            window.history.pushState({}, '', '/login');
            btn.innerHTML = 'LogIn'
            btn.style.background = '';
        }
    }
}

btn.addEventListener('click', (event) => {
    event.preventDefault();
    if (btn.textContent === 'LogIn') {
        login(xmlhttp);
    } else {
        logout(xmlhttp);
    }
});