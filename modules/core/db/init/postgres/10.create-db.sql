-- begin EXCEED_GATE
create table EXCEED_GATE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ integer not null,
    AVAILABLE_FROM time not null,
    AVAILABLE_TO time not null,
    --
    primary key (ID)
)^
-- end EXCEED_GATE
-- begin EXCEED_PLAN_IN
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
    DRIVER_PHONE varchar(10),
    STATUS integer,
    STATE integer,
    GATE integer,
    REG_DATE timestamp,
    AT_GATES_DATE timestamp,
    DEPART_ACCEPTED_DATE timestamp,
    DEPART_DATE timestamp,
    --
    primary key (ID)
)^
-- end EXCEED_PLAN_IN
-- begin EXCEED_CLIENT
create table EXCEED_CLIENT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CODE integer not null,
    NAME varchar(200) not null,
    ADDRESS varchar(200),
    CONTACT varchar(100),
    --
    primary key (ID)
)^
-- end EXCEED_CLIENT
-- begin EXCEED_PLAN_IN_CLIENT_LINK
create table EXCEED_PLAN_IN_CLIENT_LINK (
    CLIENT_ID uuid,
    PLAN_IN_ID uuid,
    primary key (CLIENT_ID, PLAN_IN_ID)
)^
-- end EXCEED_PLAN_IN_CLIENT_LINK
