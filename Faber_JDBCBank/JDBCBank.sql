create table BankAccount (
accId serial primary key,
accBalance float
);

create table BankUser (
uId serial primary key,
uUsername varchar(30) unique,
uPassword varchar(30),
uLegalName varchar(60),
uAge integer,
userTypeId integer
);

-- Join table for BankUser and BankAccount
create table UserAccount (
openingId serial primary key,
uId integer,
accId integer
);

-- Look-up table for user type
create table UserType (
userTypeId integer primary key,
userType varchar(8)
);


--Foreign keys
alter table BankUser add constraint fk_BankUserUserTypeId foreign key (userTypeId) references UserType(userTypeId) on delete cascade;
alter table UserAccount add constraint fk_UserBankAccountUId foreign key (uId) references BankUser(uId) on delete cascade;
alter table UserAccount add constraint fk_UserBankAccountAccId foreign key (accId) references BankAccount(accId) on delete cascade;

--Initial data for lookup tables and staff
insert into UserType values (1,'Customer'),(2,'Admin');
insert into BankUser(uUsername, uPassword, uLegalName, uAge, userTypeId) values ('admin1','password','The Boss',23,2);



--Functions and Triggers

--Auto-fill status ID and account balance of new accounts
create or replace function acc_insert()
returns trigger as $$
begin
	if(TG_OP = 'INSERT') then
	new."accbalance" = (0);
	end if;
	return new;
end;
$$ language plpgsql;

create trigger bankaccount_insert
before insert on "bankaccount"
for each row
execute function acc_insert();

create or replace function user_delete()
returns trigger as $$
begin
	if(TG_OP = 'DELETE') then
	delete from bankaccount where accId in
	(select accId from useraccount where uid = old.uid);
	end if;
	return new;
end;
$$ language plpgsql;

create trigger bankuser_delete
before delete on "bankuser"
for each row 
execute function user_delete();


--Create new account application tied to user
create or replace function new_single_account(in int)--In: user ID, status
returns integer
as $$
declare	
	newAccountId integer;
begin
	insert into bankaccount (accbalance) values(0);
	select currval(pg_get_serial_sequence('bankaccount','accid')) into newAccountId; 
	insert into useraccount (uid, accid) values($1, newAccountId);
	return newAccountId;
end;
$$ language plpgsql;

create or replace function new_joint_account(in int, in int)--In: user1 ID, user2 ID, account status
returns integer
as $$
declare	
	newAccountId integer;
begin
	insert into bankaccount (accbalance) values(0);
	select currval(pg_get_serial_sequence('bankaccount','accid')) into newAccountId; 
	insert into useraccount (uid, accid) values($1, newAccountId);
	insert into useraccount (uid, accid) values($2, newAccountId);
	return newAccountId;
end;
$$ language plpgsql;

create or replace function delete_user_and_accounts(in int)
returns integer
as $$
begin
	delete from bankaccount where accId in
	(select accId from useraccount where uid = $1);
	delete from bankuser where uid = $1;
	return 1;
end;
$$ language plpgsql;

create or replace function delete_user_by_name(in text)
returns integer
as $$
begin
	delete from bankaccount where accid in ( select accid from bankaccount where accId in
	(select accId from useraccount where uid in
	(select uid from bankuser where uusername = $1))
	except
	select accId from bankaccount where accId in
	(select accId from useraccount where uid in
	(select uid from bankuser where uusername != $1)));
	delete from bankuser where uusername = $1;
	return 1;
end;
$$ language plpgsql;
