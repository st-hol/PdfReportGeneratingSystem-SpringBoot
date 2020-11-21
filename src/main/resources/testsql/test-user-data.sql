INSERT INTO `roles` (`id_role`, `name_role`) VALUES ('0', 'INSPECTOR');
INSERT INTO `roles` (`id_role`, `name_role`) VALUES ('1', 'CLIENT');

INSERT INTO `persons` (`id_person`, `first_name`, `last_name`, `password`, `username`)
VALUES ('1', 'Test1', 'Test1', '$2a$10$s4dMR2mIIJvGk5myQx3jc.ehBV0S.JWjorjbdPuuhIjvaVIbxxSK6', 'test1@gmail.com');
INSERT INTO `persons` (`id_person`, `first_name`, `last_name`, `password`, `username`)
 VALUES ('2', 'Test2', 'Test2', '$2a$10$s4dMR2mIIJvGk5myQx3jc.ehBV0S.JWjorjbdPuuhIjvaVIbxxSK6', 'test2@gmail.com');
INSERT INTO `persons` (`id_person`, `first_name`, `last_name`, `password`, `username`)
 VALUES ('3', 'Test3', 'Test3', '$2a$10$s4dMR2mIIJvGk5myQx3jc.ehBV0S.JWjorjbdPuuhIjvaVIbxxSK6', 'test3@gmail.com');
INSERT INTO `persons` (`id_person`, `first_name`, `last_name`, `password`, `username`)
 VALUES ('4', 'admin', 'admin', '$2a$10$s4dMR2mIIJvGk5myQx3jc.ehBV0S.JWjorjbdPuuhIjvaVIbxxSK6', 'admin@gmail.com');
INSERT INTO `persons` (`id_person`, `first_name`, `last_name`, `password`, `username`)
 VALUES ('5', 'client', 'client', '$2a$10$s4dMR2mIIJvGk5myQx3jc.ehBV0S.JWjorjbdPuuhIjvaVIbxxSK6', 'client@gmail.com');


INSERT INTO `persons_has_roles` (`id_role`, `id_person`) VALUES ('1', '1');
INSERT INTO `persons_has_roles` (`id_role`, `id_person`) VALUES ('1', '2');
INSERT INTO `persons_has_roles` (`id_role`, `id_person`) VALUES ('1', '3');
INSERT INTO `persons_has_roles` (`id_role`, `id_person`) VALUES ('0', '4');
INSERT INTO `persons_has_roles` (`id_role`, `id_person`) VALUES ('1', '5');


INSERT INTO `completed_reports` (`id`, `completion_time`, `id_person`, `report_type`)
VALUES ('1', '2020-10-26 23:11:42', '1', null);
INSERT INTO `completed_reports` (`id`, `completion_time`, `id_person`, `report_type`)
VALUES ('2', '2020-10-26 23:11:42', '2', null);
INSERT INTO `completed_reports` (`id`, `completion_time`, `id_person`, `report_type`)
 VALUES ('3', '2020-10-26 23:11:42', '3', null);

