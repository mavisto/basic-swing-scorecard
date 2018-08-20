/* Create basic golf course table */
Create table golfcourse (
	courseId bigint auto_increment NOT NULL,
	name varchar(120) NOT NULL,
	address varchar(120),
	phone varchar(20),
	primary key(courseId)
);

/* Create course data table */
create table golfcoursedata (
	courseId bigint NOT NULL,
	courseDataId bigint auto_increment NOT NULL,
	teeColour varchar(20) NOT NULL,
	primary key(courseDataId, courseId),
	foreign key (courseId) references golfcourse(courseId)
);

/* Create golf hole record */

create table golfholerecord (
	id bigint auto_increment NOT NULL,
	courseId bigint NOT NULL,
	courseDataId bigint NOT NULL,
	holeNumber bigint NOT NULL,
	holeYardage bigint NOT NULL,
	holePar bigint NOT NULL,
	holeSI bigint NOT NULL,
	primary key (id, courseId, courseDataId),
	foreign key (courseId, courseDataId) references golfcoursedata (courseId, courseDataId)

);

/* Create golf hole data
create table golfholedata (
	id bigint auto_increment NOT NULL,
	courseId bigint NOT NULL,
	par bigint NOT NULL,
	si bingint NOT NULL,
	CONSTRAINT pk_ primary key(id,courseId),
	foreign key (courseId) references golfcourse(id,courseId)
	
	
	hole1 int,
	hole2 int,
	hole3 int,
	hole4 int,
	hole5 int,
	hole6 int,
	hole7 int,
	hole8 int,
	hole9 int,
	hole10 int,
	hole11 int,
	hole12 int,
	hole13 int,
	hole14 int,
	hole15 int,
	hole16 int,
	hole17 int,
	hole18 int,
);*/