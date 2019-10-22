-- Role: java

-- DROP ROLE java;

CREATE ROLE java LOGIN
    PASSWORD 'javapwd'
    SUPERUSER INHERIT CREATEDB CREATEROLE REPLICATION;
GRANT pg_monitor TO java;
GRANT pg_read_all_settings TO java;
GRANT pg_read_all_stats TO java;
GRANT pg_signal_backend TO java;
GRANT pg_stat_scan_tables TO java;


-- Database: java4postgres

-- DROP DATABASE java4postgres;
-- CONNECTION LIMIT = -1;

CREATE DATABASE java
    WITH OWNER = java
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE ROLE tw LOGIN
    PASSWORD 'java4postgrespwd'
    SUPERUSER INHERIT CREATEDB CREATEROLE REPLICATION;
GRANT pg_monitor TO tw;
GRANT pg_read_all_settings TO tw;
GRANT pg_read_all_stats TO tw;
GRANT pg_signal_backend TO tw;
GRANT pg_stat_scan_tables TO tw;


-- Database: java4postgres

-- DROP DATABASE java4postgres;
-- CONNECTION LIMIT = -1;

CREATE DATABASE tw
    WITH OWNER = tw
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;