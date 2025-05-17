drop table if exists employee_mst ;
drop table if exists group_mst ;
--
-- Name: employee_mst; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE employee_mst
(
    company_cd       varchar(2)     DEFAULT ' '   NOT NULL,
    employee_cd      varchar(7)     DEFAULT ' '   NOT NULL,
    employee_nm      varchar(50)    DEFAULT ' '   NOT NULL,
    group_cd         varchar(2)     DEFAULT ' '   NOT NULL,
    grade_cd         varchar(1)     DEFAULT ' ',
    zip_cd           varchar(7)     DEFAULT ' ',
    address          varchar(100)   DEFAULT ' ',
    telephone_no     varchar(10)    DEFAULT ' ',
    fax_no           varchar(10)    DEFAULT ' ',
    cellularphone_no varchar(11)    DEFAULT ' ',
    mail_address     varchar(50)    DEFAULT ' ',
    join_date        TIMESTAMP(9) WITH TIME ZONE,
    birth_date       TIMESTAMP(9) WITH TIME ZONE,
    age              varchar(3)     DEFAULT ' ',
    gender           varchar(1)     DEFAULT ' ',
    remark           varchar(100)   DEFAULT ' ',
    created_by       varchar(10)    DEFAULT ' '   NOT NULL,
    date_created     timestamp(6)   DEFAULT now() NOT NULL,
    last_updated_by  varchar(10)    DEFAULT ' '   NOT NULL,
    last_updated     timestamp(6)   DEFAULT now() NOT NULL,
    update_program   varchar(10)    DEFAULT ' '   NOT NULL,
    update_counter   numeric(38, 0) DEFAULT 0     NOT NULL
);


--
-- Name: group_mst; Type: TABLE; Schema: public; Owner: -; Tablespace:
--

CREATE TABLE group_mst
(
    group_cd        varchar(2)     DEFAULT ' '   NOT NULL,
    group_nm        varchar(100)   DEFAULT ' '   NOT NULL,
    created_by      varchar(10)    DEFAULT ' '   NOT NULL,
    date_created    timestamp(6)   DEFAULT now() NOT NULL,
    last_updated_by varchar(10)    DEFAULT ' '   NOT NULL,
    last_updated    timestamp(6)   DEFAULT now() NOT NULL,
    update_program  varchar(10)    DEFAULT ' '   NOT NULL,
    update_counter  numeric(38, 0) DEFAULT 0     NOT NULL
);

-- Name: employee_mst_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace:
--

ALTER TABLE employee_mst
    ADD CONSTRAINT employee_mst_pkey PRIMARY KEY (company_cd, employee_cd);

ALTER TABLE group_mst
    ADD CONSTRAINT group_mst_pkey PRIMARY KEY (group_cd);