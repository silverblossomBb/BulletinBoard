create table loginInfo (
	`no` int not null,
	id varchar(12) not null,
	`name` varchar(12) not null,
	timeLog timestamp not null,
	image varchar(255)
);


create table notice (
	`no` int not null,
	title varchar(255) not null,
	content varchar(255),
	`name` varchar(12) not null,
	timeLog timestamp not null,
	delYn char(1) default 'N'
);


create table upload (
	`no` int not null,
	noticeNo int not null,
	id varchar(12) not null,
	`name` varchar(12) not null,
	originName varchar(255) not null,
	`uuid` varchar(255) not null,
	timeLog timestamp not null
);


create table download (
	`no` int not null,
	id varchar(12) not null,
	`uuid` varchar(255) not null,
	timeLog timestamp not null
);