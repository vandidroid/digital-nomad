insert into country (id, name, area, population) values (1, 'Hungary', 93030, 9730000);
insert into country (id, name, area, population) values (2, 'Japan', 377975, 125927902);
insert into country (id, name, area, population) values (3, 'Brazil', 8515767, 214047375);
insert into country (id, name, area, population) values (4, 'Namibia', 825615, 2550226 );

insert into location (id, name, country_id, area, population) values (1, 'Budapest', 1, 525.2, 1752286);
insert into location (id, name, country_id, area, population) values (2, 'Tokyo', 2, 2194.07, 13988129);
insert into location (id, name, country_id, area, population) values (3, 'Rio de Janeiro', 3, 1182.296, 6747815);

insert into digital_nomad (id, email, first_name, last_name, nickname, gender, location_id)
values (1, 'anonymous@gmail.com', null, null, 'Anonymous', 0, 1);

insert into digital_nomad (id, email, first_name, last_name, nickname, gender, location_id)
values (11, 'vandidroid@gmail.com', 'Andrea', 'Vincze-Palkó', 'Andi', 1, 2);

