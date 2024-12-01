const userList = document.getElementById('userList');
const searchInput = document.getElementById('searchInput');
const userCount = document.getElementById('userCount');
const addUserBtn = document.getElementById('addUserBtn');
const addUserModal = new bootstrap.Modal(document.getElementById('addUserModal'));
const addUserForm = document.getElementById('addUserForm');

searchInput.addEventListener('input', (e) => {
    const searchTerm = e.target.value.toLowerCase();
    const users = userList.querySelectorAll('.user-list-item');

    users.forEach(user => {
        const email = user.querySelector('span').textContent.toLowerCase();
        user.style.display = email.includes(searchTerm) ? '' : 'none';
    });
});

userList.addEventListener('click', (e) => {
    const deleteBtn = e.target.closest('.delete-user-button');
    if (deleteBtn) {
        const userItem = deleteBtn.closest('.user-list-item');
        const username = userItem.querySelector('span').textContent;
        console.log(username);
        fetch(`delete/${username}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('jwt')}`
            }
        });
        userItem.remove();
        updateUserCount();
    }
});

addUserBtn.addEventListener('click', () => {
    addUserModal.show();
});

addUserForm.addEventListener('submit', (e) => {
    e.preventDefault();
});

function updateUserCount() {
    const users = userList.querySelectorAll('.user-list-item');
    userCount.textContent = users.length;
}

async function fetchUsers() {
    let users = await fetch(
        '/dashboard/users', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('jwt')}`,
            }
        }).then(r => r.json())
        .catch(err => console.log(err));


    history.pushState(null, '', '/dashboard');
    document.getElementById('userList').innerHTML =
    `
        ${users.map(user => `
            <li class="list-group-item list-group-item-action user-list-item d-flex justify-content-between align-items-center">
                <div class="d-flex align-items-center">
                    <i class="bi bi-person-circle me-3 text-secondary"></i>
                    <span class="w-75">${user.username}</span>
                </div>
                <button class="delete-user-button btn btn-sm btn-outline-danger">
                    <i class="bi bi-trash"></i>
                </button>
            </li>
        `).join(`\n`)}
    `;
    updateUserCount();
}