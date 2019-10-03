CREATE TABLE d_medicine(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    date_of_use DATE NOT NULL,
    description VARCHAR(1000) NOT NULL,
    price DECIMAL(4,2),
    PRIMARY KEY(id)
);
CREATE TABLE D_user(
    id INT NOT NULL AUTO_INCREMENT,
    login VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    password VARCHAR(200) NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRiMARY KEY(id)
);
CREATE TABLE d_warehouse(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    list_of_medicines INT NOT NULL,
    list_of_users INT NOT NULL,
    PRIMARY KEY(id)
);
ALTER TABLE d_warehouse ADD FOREIGN KEY (list_of_medicines) REFERENCES d_medicine(id);
ALTER TABLE d_warehouse ADD FOREIGN KEY (list_of_users) REFERENCES d_user(id);