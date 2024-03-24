create table EXCEED_PLANIN_EVENT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    REG_NUMBER varchar(10),
    VEHICLE_NUMBER varchar(20),
    STATUS integer,
    STATE integer,
    EVENT_DATE timestamp,
    --
    primary key (ID)
);