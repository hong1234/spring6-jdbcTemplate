DROP TABLE IF EXISTS people;

create table people (
  id int NOT NULL AUTO_INCREMENT,
  first_name varchar(20) NOT NULL,
  last_name varchar(20) NOT NULL,
  age int NOT NULL,
  gender enum ('Male', 'Female', 'Third') NOT NULL,
  created_on timestamp DEFAULT NULL,
  updated_on timestamp DEFAULT NULL,
--   created_on datetime DEFAULT NULL,
--   updated_on datetime DEFAULT NULL,
  PRIMARY KEY (id)
);