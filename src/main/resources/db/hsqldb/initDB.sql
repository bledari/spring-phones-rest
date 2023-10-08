DROP TABLE phones IF EXISTS;
DROP TABLE phone_api_info IF EXISTS;

CREATE TABLE phone_api_info (
    id            INTEGER IDENTITY PRIMARY KEY,
    phone_id      VARCHAR(200) UNIQUE,
    brand         VARCHAR(200),
    technology    VARCHAR(200),
    gprs          VARCHAR(200),
    edge          VARCHAR(200),
    announced     VARCHAR(200),
    status        VARCHAR(200),
    dimensions    VARCHAR(200),
    weight        VARCHAR(200),
    sim           VARCHAR(200),
    display_type  VARCHAR(200),
    display_size  VARCHAR(200)
);
CREATE INDEX phone_api_info_index_phone_id ON phone_api_info(phone_id);

CREATE TABLE phones (
  id            INTEGER IDENTITY PRIMARY KEY,
  phone_id      INTEGER,
  availability  BOOLEAN,
  booked_at     DATETIME(0),
  booker        VARCHAR(200)
);
CREATE INDEX phones_phone_id ON phones(phone_id);
CREATE INDEX phones_booker ON phones(booker);

ALTER TABLE phones ADD CONSTRAINT fk_phones_phone_api_info_phone_id FOREIGN KEY (phone_id) REFERENCES phone_api_info (id);
