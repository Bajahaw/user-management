// const btn = document.getElementById('btn');
// const user= document.getElementById('username');
// const pass= document.getElementById('password');
// const signUp = document.getElementById('signup');

//const xmlhttp = new XMLHttpRequest();

// function login(xmlhttp){
//     xmlhttp.open("POST", "/login");
//     xmlhttp.setRequestHeader('Content-Type', 'application/json');
//     const credentials = JSON.stringify({username: user.value, password: pass.value});
//     xmlhttp.send(credentials);
//     xmlhttp.onload = function() {
//         let state = document.getElementById('state');
//         state.innerHTML = this.response;
//
//         // if (this.status === 200){
//         //     window.history.pushState({}, '', '/user');
//         //     btn.innerHTML = 'LogOut';
//         //     btn.style.background = 'red';
//         //     user.value = '';
//         //     pass.value = '';
//         // }
//         if (this.status === 302) {
//             window.location.href = this.getResponseHeader('Location');
//         }
//         else if (this.status === 404){
//             state.innerHTML = 'Wrong email or password';
//             pass.value = '';
//         }
//     }
// }
// function logout(xmlhttp){
//     xmlhttp.open("GET", "/logout");
//     xmlhttp.send();
//     xmlhttp.onload = function (){
//         document.getElementById('state').innerHTML =
//             this.response;
//         if (this.status === 200){
//             window.history.pushState({}, '', '/login');
//             btn.innerHTML = 'LogIn';
//             btn.style.background = '';
//         }
//     }
// }
// function signup(xmlhttp){
//     xmlhttp.open("PUT", "/add");
//     xmlhttp.setRequestHeader('Content-Type', 'application/json');
//     const credentials = JSON.stringify({username: user.value, password: pass.value});
//     xmlhttp.send(credentials);
//     xmlhttp.onload = function() {
//         let state = document.getElementById('state');
//         state.innerHTML = this.response;
//
//         if (this.status === 200){
//             window.history.pushState({}, '', '/login');
//             btn.innerHTML = 'LogIn';
//             user.value = '';
//             pass.value = '';
//         }
//     }
// }


// btn.addEventListener('click', (event) => {
//     event.preventDefault();
//     if (btn.textContent === 'LogIn') {
//         login(xmlhttp);
//     } else if(btn.textContent === 'LogOut'){
//         logout(xmlhttp);
//     } else {
//         signup(xmlhttp);
//     }
// });
// signUp.addEventListener('click', (evt)=>{
//     evt.preventDefault();
//     window.history.pushState({}, '', '/signup   ');
//     btn.innerHTML = 'SignUp';
// });