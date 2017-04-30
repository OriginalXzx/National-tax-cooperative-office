

/*==============================================================*/
/* Table: complain                                              */
/*==============================================================*/
create table complain
(
   comp_id              varchar(32) not null,
   comp_company         varchar(100),
   comp_name            varchar(20),
   comp_mobile          varchar(20),
   is_NM                bool,
   comp_time            datetime,
   comp_title           varchar(200) not null,
   to_comp_name         varchar(20),
   to_comp_dept         varchar(100),
   comp_content         text,
   state                varchar(1),
   primary key (comp_id)
);


/*==============================================================*/
/* Table: comp_reply                                            */
/*==============================================================*/
create table comp_reply
(
   comp_id              varchar(32) not null,
   reply_id             varchar(32) not null,
   replyer              varchar(20),
   reply_dept           varchar(100),
   reply_time           datetime,
   reply_content                varchar(300)
);

alter table comp_reply add constraint FK_complain_deal foreign key (comp_id)
      references complain (comp_id) on delete restrict on update restrict;
