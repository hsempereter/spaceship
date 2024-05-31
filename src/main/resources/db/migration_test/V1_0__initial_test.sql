
CREATE TABLE spaceship (
    id int4 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    name varchar(200) NOT NULL,
    
    CONSTRAINT spaceship_pk PRIMARY KEY (id),
    CONSTRAINT spaceship_unique UNIQUE (name)
);
