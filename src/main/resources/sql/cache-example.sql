drop database if exists `cache-example-db`;
create database `cache-example-db`;
use `cache-example-db`;

create table if not exists `users` (
	`id` int not null auto_increment,
    `name` varchar(50) not null,
    `creationTime` timestamp default current_timestamp,
    primary key (`id`)
) auto_increment = 1, engine = `innoDB`;

create table if not exists `followers` (
	`followerId` int not null,
    `followingId` int not null,
    primary key (`followerId`, `followingId`),
    constraint `followerId_foreign_key_userId` foreign key (`followerId`) references users(`id`),
    constraint `followingId_foreign_userId` foreign key (`followingId`) references users(`id`)
) engine = `innoDB`;

create table if not exists `tweets` (
	`id` int not null auto_increment,
    `userId` int null,
    `creationTime` timestamp default current_timestamp,
    `content` varchar (200) not null,
    primary key (`id`),
    constraint `userId_foreign_key_userId` foreign key (`userId`) references users(`id`)
) auto_increment = 1, engine = `innoDB`;




--insert into `users`
--( `name` ) value
--( "User 1" ),
--( "User 2" ),
--( "User3 " );
--
--insert into `followers`
--(`followerId`, `followingId`) value
--(1, 2),
--(1, 3);
--
--insert into `tweets`
--(`userId`, `content`) value
--(2, "user 2 tweet 1"),
--(2, "user 2 tweet 2"),
--(2, "user 2 tweet 3"),
--(3, "user 3 tweet 1"),
--(3, "user 3 tweet 2"),
--(3, "user 3 tweet 3");
--
--select  t.id, t.userId, u.name, t.creationTime, t.content from `followers` `f`
--left join `users` `u` on u.id = f.followingId
--left join `tweets` `t` on f.followingId = t.userId
--where f.followerId = 1;