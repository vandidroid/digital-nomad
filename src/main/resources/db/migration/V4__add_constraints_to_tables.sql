alter table if exists country add constraint UK_country_name unique (name);

alter table if exists location add constraint FK_country_id_ref_country foreign key (country_id) references country;

alter table if exists digital_nomad add constraint UK_nomad_email unique (email);
alter table if exists digital_nomad add constraint UK_nomad_nickname unique (nickname);
alter table if exists digital_nomad add constraint FK_nomad_location_id_location foreign key (location_id) references location;
