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
                const li = document.createElement('li'); // 새로운 리스트 아이템을 만듭니다.
                // 할 일과 날짜 정보를 표시합니다.
                li.textContent = `${todo.task} - ${todo.date} (${todo.completed ? '완료' : '미완료'})`;
                li.className = "list-group-item"; // 부트스트랩 스타일을 적용합니다.
                todoList.appendChild(li); // 목록에 추가합니다.
            });
        });
}

function addTodo() {
    const taskInput = document.getElementById("task"); // 할 일 입력 필드를 가져옵니다.
    const dateInput = document.getElementById("date"); // 날짜 입력 필드를 가져옵니다.
    const task = taskInput.value; // 입력된 할 일 값을 가져옵니다.
    const date = dateInput.value; // 입력된 날짜 값을 가져옵니다.

    fetch('/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded', // 데이터 형식 설정
        },
        body: new URLSearchParams({ task, date }) // 할 일과 날짜 값을 URL 형식으로 변환합니다.
    })
        .then(response => response.json()) // 서버 응답을 JSON 형식으로 변환합니다.
        .then(todo => {
            fetchTodos(); // TODO 목록을 새로 고칩니다.
            taskInput.value = ''; // 할 일 입력 필드를 초기화합니다.
            dateInput.value = ''; // 날짜 입력 필드를 초기화합니다.
        });
}
