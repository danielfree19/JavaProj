CREATE TABLE person (
  idPerson int NOT NULL,
  name varchar(45) DEFAULT NULL,
  phone varchar(45) DEFAULT NULL,
  mail varchar(45) DEFAULT NULL,
  PRIMARY KEY (idPerson)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE employee (
  idemployee int NOT NULL,
  name varchar(45) DEFAULT NULL,
  phone varchar(45) DEFAULT NULL,
  mail varchar(45) DEFAULT NULL,
  address varchar(45) DEFAULT NULL,
  role varchar(45) DEFAULT NULL,
  PRIMARY KEY (idemployee)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE employeelogin (
  UserName varchar(45) NOT NULL,
  Password varchar(45) NOT NULL,
  accessID int NOT NULL,
  employeeID int DEFAULT NULL,
  PRIMARY KEY (accessID),
  KEY idemployee_idx (employeeID),
  CONSTRAINT idemployee FOREIGN KEY (employeeID) REFERENCES employee (idemployee)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE jobaplication (
  applicationID int NOT NULL,
  jobNum int DEFAULT NULL,
  CV varchar(45) DEFAULT NULL,
  idPerson int DEFAULT NULL,
  PRIMARY KEY (applicationID),
  KEY idPerson_idx (idPerson),
  CONSTRAINT jobaplication_ibfk_1 FOREIGN KEY (idPerson) REFERENCES person (idPerson)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE tickets (
  ticketNum int NOT NULL,
  description varchar(85) DEFAULT NULL,
  status varchar(45) DEFAULT NULL,
  idPerson int DEFAULT NULL,
  PRIMARY KEY (ticketNum),
  KEY idPerson_idx (idPerson),
  CONSTRAINT idPerson FOREIGN KEY (idPerson) REFERENCES person (idPerson)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

