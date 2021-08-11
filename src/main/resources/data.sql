insert into country (id, name) values (1, 'Hungary');
insert into country (id, name) values (2, 'Japan');

insert into location (id, name, country_id) values (1, 'Budapest', 1);
insert into location (id, name, country_id) values (2, 'Tokyo', 2);

insert into digital_nomad (id, email, first_name, last_name, nickname, gender, location_id)
values (11, 'vandidroid@gmail.com', 'Andrea', 'Vincze-Palkó', 'Andi', 1, 2);
