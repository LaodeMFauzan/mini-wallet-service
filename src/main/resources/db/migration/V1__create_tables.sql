CREATE TABLE IF NOT EXISTS customer (
    id varchar(100) NOT NULL PRIMARY KEY,
    name varchar(100) NOT NULL
);

CREATE TABLE wallet (
  id varchar(100) NOT NULL,
  customer_id varchar(100) NOT NULL,
  status boolean  NOT NULL,
  enabled_at TIMESTAMP NOT NULL,
  disabled_at TIMESTAMP NOT NULL,
  balance DECIMAL(20,2) NOT NULL,
  PRIMARY KEY (id),
  foreign key (customer_id) references customer(id)
);

CREATE TABLE deposit (
  id varchar(100) NOT NULL ,
  customer_id varchar(100) NOT NULL,
  wallet_id varchar(100) NOT NULL,
  deposited_at TIMESTAMP NOT NULL,
  amount DECIMAL(20,2) NOT NULL,
  reference_id varchar(100) NOT NULL,
  PRIMARY KEY (id),
  foreign key (customer_id) references customer(id),
  foreign key (wallet_id) references wallet(id)
);

CREATE TABLE withdraw (
  id varchar(100) NOT NULL ,
  customer_id varchar(100) NOT NULL,
  wallet_id varchar(100) NOT NULL,
  withdrawn_at TIMESTAMP NOT NULL,
  amount DECIMAL(20,2) NOT NULL,
  reference_id varchar(100) NOT NULL,
  PRIMARY KEY (id),
  foreign key (customer_id) references customer(id),
  foreign key (wallet_id) references wallet(id)
);
