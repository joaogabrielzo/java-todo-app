USE 'java_todo';
CREATE TABLE 'tasks' (
'id' SERIAL,
'user' VARCHAR(30),
'task' VARCHAR(70),
'due_date' Date
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;