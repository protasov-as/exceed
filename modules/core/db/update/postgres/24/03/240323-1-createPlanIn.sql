create table EXCEED_PLAN_IN (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    REG_NUMBER varchar(10) not null,
    ORDER_NUMBER varchar(50),
    ARRIVAL_PLAN_TIME timestamp,
    VEHICLE_NUMBER varchar(20),
    WEIGHT_CAPACITY integer,
    DRIVER_NAME varchar(100),
    DRIVER_PHONE varchar(20),
    STATUS integer,
    STATE integer,
    GATE varchar(5),
    REG_DATE timestamp,
    AT_GATES_DATE timestamp,
    DEPART_ACCEPTED_DATE timestamp,
    DEPART_DATE timestamp,
    --
    primary key (ID)
);