create table log_transaction (
  tr_id varchar(36),
  tr_type varchar(256),
  tr_status varchar(25)
);

create table log_message (
  m_tr_id varchar(36),
  m_message varchar(256),
  m_status varchar(25)
);

create table my_data (
  my_value varchar(256)
);
