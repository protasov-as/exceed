-- begin EXCEED_GATE
create unique index IDX_EXCEED_GATE_UK_NUMBER_ on EXCEED_GATE (NUMBER_) where DELETE_TS is null ^
-- end EXCEED_GATE
-- begin EXCEED_CLIENT
create unique index IDX_EXCEED_CLIENT_UK_CODE on EXCEED_CLIENT (CODE) where DELETE_TS is null ^
-- end EXCEED_CLIENT
-- begin EXCEED_PLAN_IN_CLIENT_LINK
alter table EXCEED_PLAN_IN_CLIENT_LINK add constraint FK_PLAINCLI_ON_CLIENT foreign key (CLIENT_ID) references EXCEED_CLIENT(ID)^
alter table EXCEED_PLAN_IN_CLIENT_LINK add constraint FK_PLAINCLI_ON_PLAN_IN foreign key (PLAN_IN_ID) references EXCEED_PLAN_IN(ID)^
-- end EXCEED_PLAN_IN_CLIENT_LINK
