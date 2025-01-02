document.addEventListener("DOMContentLoaded", function() {
    fetchTodos(); // 페이지가 로드될 때 TODO 목록을 가져옵니다.

    document.getElementById("todoForm").addEventListener("submit", function(event) {
        event.preventDefault(); // 기본 폼 제출 동작을 막습니다.
        addTodo(); // 새로운 할 일을 추가합니다.
    });
});

function fetchTodos() {
    fetch('/todos') // 서버에서 TODO 목록을 가져옵니다.
        .then(response => response.json()) // 서버 응답을 JSON 형식으로 변환합니다.
        .then(data => {
            const todoList = document.getElementById('todoList');
            todoList.innerHTML = ''; // 목록을 초기화합니다.
            data.forEach(todo => {
                const li = document.createElement('li');
                li.className = "list-group-item d-flex justify-content-between align-items-center";

                const checkbox = document.createElement('input');
                checkbox.type = 'checkbox';
                checkbox.checked = todo.completed;
                checkbox.onclick = () => toggleTodoCompletion(todo);

                const taskText = document.createElement('span');
                taskText.textContent = `${todo.task} - ${todo.date} (${todo.completed ? '완료' : '미완료'})`;

                const deleteButton = document.createElement('button');
                deleteButton.textContent = '삭제';
                deleteButton.className = "btn btn-danger btn-sm ml-2";
                deleteButton.onclick = () => deleteTodo(todo.id);

                li.appendChild(checkbox);
                li.appendChild(taskText);
                li.appendChild(deleteButton);
                todoList.appendChild(li);
            });
        });
}

function addTodo() {
    const taskInput = document.getElementById("task");
    const dateInput = document.getElementById("date");
    const task = taskInput.value;
    const date = dateInput.value;

    fetch('/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({ task, date })
    })
        .then(response => response.json())
        .then(todo => {
            fetchTodos(); // TODO 목록을 새로 고칩니다.
            taskInput.value = ''; // 할 일 입력 필드를 초기화합니다.
            dateInput.value = ''; // 날짜 입력 필드를 초기화합니다.
        });
}

function toggleTodoCompletion(todo) {
    fetch(`/update/${todo.id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({ completed: !todo.completed })
    })
        .then(() => {
            fetchTodos(); // 수정 후 TODO 목록을 새로 고칩니다.
        });
}

function deleteTodo(id) {
    if (confirm("정말로 삭제하시겠습니까?")) {
        fetch(`/delete/${id}`, {
            method: 'DELETE'
        })
            .then(() => {
                fetchTodos(); // 삭제 후 TODO 목록을 새로 고칩니다.
            });
    }
}
