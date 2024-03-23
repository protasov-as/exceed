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
);