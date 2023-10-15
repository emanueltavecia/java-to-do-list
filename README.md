# To Do List

Repository of To Do List back-end application.

## How to use

- You can access this application in https://to-do-list-r19j.onrender.com with a REST Client.

### Registering a new user
- To register a new user, do a POST request from https://to-do-list-r19j.onrender.com/users/ and send a JSON body like this:
```json
{
  "name": "Your Name",
  "username": "your.user",
  "password": "yourpassword"
}
```

### Registering a new task
- To register a new task, do a POST request from https://to-do-list-r19j.onrender.com/tasks/ with "Basic Auth" and send a JSON body like this:
- Note: Title has a limit of 50 characters.
```json
{
    "description": "Task description.",
    "title": "Task Title",
    "startAt": "2023-10-22T07:56:22",
    "endAt": "2023-10-22T07:56:22",
    "priority": "High"
}
```

### Requesting tasks
- To request your tasks, do a GET request from https://to-do-list-r19j.onrender.com/tasks/ with "Basic Auth".

### Updating tasks
- To update your tasks, do a PUT request from https://to-do-list-r19j.onrender.com/tasks/{task_id} with "Basic Auth" and send a JSON body like this (minimum of one information):
- URL example: https://to-do-list-r19j.onrender.com/tasks/1ce884c2-af38-4281-abc4-7c13e280724e
- Note: Title has a limit of 50 characters.
```json
{
    "description": "Task description.",
    "title": "Task Title",
    "startAt": "2023-10-22T07:56:22",
    "endAt": "2023-10-22T07:56:22",
    "priority": "High"
}
```

## Project Information
* Technology: Java;
* Made by Emanuel Tavecia with Rocketseat Free Java Course.