CREATE TABLE IF NOT EXISTS worker (
    ID IDENTITY PRIMARY KEY,
    NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) BETWEEN 2 AND 1000),
    BIRTHDAY DATE CHECK (BIRTHDAY > '1900-01-01'),
    LEVEL VARCHAR(10) NOT NULL CHECK (LEVEL IN ('Trainee', 'Junior', 'Middle', 'Senior')),
    SALARY INT CHECK (SALARY >= 100 AND SALARY <= 100000)
);

CREATE TABLE IF NOT EXISTS client(
    ID IDENTITY PRIMARY KEY,
    NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) BETWEEN 2 AND 1000)
);

CREATE TABLE IF NOT EXISTS project(
    ID IDENTITY PRIMARY KEY,
    CLIENT_ID BIGINT NOT NULL,
    START_DATE DATE,
    FINISH_DATE DATE,
    CONSTRAINT CLIENT_ID_DK FOREIGN KEY(CLIENT_ID) REFERENCES client(ID)
);

CREATE TABLE IF NOT EXISTS project_worker(
    PROJECT_ID BIGINT,
    WORKER_ID BIGINT,
    PRIMARY KEY(PROJECT_ID, WORKER_ID),
    CONSTRAINT PROJECT_ID_FK FOREIGN KEY(PROJECT_ID) REFERENCES project(ID),
    CONSTRAINT WORKER_ID_FK FOREIGN KEY(WORKER_ID) REFERENCES worker(ID)
);
