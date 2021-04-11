USE telephoneexchange;

CREATE TABLE address (
  id           VARCHAR(64) NOT NULL,
  city         VARCHAR(50) NOT NULL,
  street       VARCHAR(50) NOT NULL,
  house_number INT UNSIGNED NOT NULL,
  block        INT UNSIGNED,
  flat         INT UNSIGNED,
  PRIMARY KEY (id)
)ENGINE = INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE personal_info (
  id              varchar(64) NOT NULL,
  first_name      VARCHAR(50) NOT NULL,
  last_name       VARCHAR(50) NOT NULL,
  patronymic_name VARCHAR(50) NOT NULL,
  phone_number     VARCHAR(30) NOT NULL UNIQUE,
  address_id      varchar(64),
  PRIMARY KEY (id),
  FOREIGN KEY (address_id) REFERENCES address (id) ON UPDATE RESTRICT ON DELETE RESTRICT
)ENGINE = INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE tariff_plan (
  id             varchar(64)  NOT NULL,
  name           VARCHAR(100) NOT NULL,
  cost_per_month INT          NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
)ENGINE = INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE account (
  id             varchar(64)  NOT NULL,
  tariff_plan_id VARCHAR(100) NOT NULL,
  balance        INT          NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  FOREIGN KEY (tariff_plan_id) REFERENCES tariff_plan (id) ON UPDATE RESTRICT ON DELETE RESTRICT
)ENGINE = INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE user (
  id               varchar(64)  NOT NULL,
  login            VARCHAR(100) NOT NULL UNIQUE,
  password         VARCHAR(100) NOT NULL,
  role             INT          NOT NULL,
  blocked          BOOLEAN      NOT NULL DEFAULT false,
  personal_info_id varchar(64)  NOT NULL,
  account_id       varchar(64)  NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (personal_info_id) REFERENCES personal_info (id) ON UPDATE RESTRICT ON DELETE RESTRICT,
  FOREIGN KEY (account_id) REFERENCES account (id) ON UPDATE RESTRICT ON DELETE RESTRICT
)ENGINE = INNODB DEFAULT CHARACTER SET utf8;