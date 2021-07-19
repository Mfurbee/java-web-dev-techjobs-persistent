## Part 1: Test it with SQL

employer : varchar(255)
id : int
name : varchar(255)
skills : varchar(255)

## Part 2: Test it with SQL

select * from employer where location = 'St. Louis City';

## Part 3: Test it with SQL

drop table job;

## Part 4: Test it with SQL

select * from skill
where id in (select skills_id from job_skills)
order by name, description asc;
## This does not use "is not null" because the relationship
## is maintained in a third join table