/*1.1 Select*/
--Select the employeeId, last name, and email for records in the Employee table where last name is King.
select e."EmployeeId", e."LastName", e."Email" from "Employee" e where e."LastName" = 'King'

-- Select the city and state for the records in the Employee table where first name is Andrew and REPORTSTO is NULL.
select e."City", e."State" from "Employee" e where e."FirstName" = 'Andrew' and e."ReportsTo" is null 

/*1.2 Sub-queries*/
--Select all records from the Album table where the composer is AC/DC.
select * from "Album" a where "ArtistId" in (select a2."ArtistId" from "Artist" a2  where a2."Name" = 'AC/DC')

/*1.3 Order By*/
-- Select all albums in Album table and sort result set in descending order by title.
select * from "Album" a order by a."Title" desc

--Select first name from Customer and sort result set in ascending order by city
select c."FirstName" from "Customer" c order by c."City" 

/*1.6 Like*/
--Select all invoices with a billing address like “T%” .
--	This means where the billing address starts with T.
select * from "Invoice" i where i."BillingAddress" like 'T%'

/*1.7 Between*/
select * from "Invoice" i where i."Total" between 15 and 50

/*2.1 Insert into*/
-- Insert two complete new records into Genre table.
insert into "Genre" values(26,'Interpretive Dance'),
(27,'ASMR');

--Insert two complete new records into Employee table.
insert into "Employee" values(13,'Jenkins','Leeroy','Internet Legend',1,'1988-10-12','2003-03-04','1337 Raid Drive','Bucket','AB','Canada','T12 3Y4','+1 (123)456-7890','+1 (123)456-7890','leeroy@chinookcorp.com'),
(14,'Guy','The','Not a homunculus',2,'1988-10-13','2003-03-05','1234 Real Place','Location','AB','Canada','T23 4Y5','+1 (123)456-7891','+1 (123)456-7890','realman@chinookcorp.com');

--Insert two complete new records into Customer table.
insert into "Customer" values(62,'Person','Some','GoodCorp','123 Easy Street','Cityland','ST','USA 2','12345','+1 (123)456-7890','+1 (123)456-7890','psome@gargle.com',3),
(63,'Man','That','EvilCorp','987 Difficult Street','Otherland','ST','USA 2','12345','+1 (123)456-7890','+1 (123)456-7890','psome@gargle.com',3);

/*2.2 Update*/
--Update Aaron Mitchell in Customer table to Robert Walter.
--	Note: This should update the record with ID 32.
update "Customer" set "LastName" = 'Walter', "FirstName" = 'Robert' where "LastName" = 'Mitchell' and "FirstName" = 'Aaron'

--Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”.
--	Note: This should update the record with ID 76.
update "Artist" set "Name" = 'CCR' where "Name" = 'Creedence Clearwater Revival'

--Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).
/*
 * 	This should delete record 32, which was updated earlier.
 *	First attempt: delete from "Customer" where "FirstName" = 'Robert' and "LastName" = 'Walter'
 *		ERROR: update or delete on table "Customer" violates foreign key constraint "FK_InvoiceCustomerId" on table "Invoice"
 *		Solution: add "on delete cascade" rule to foreign keys, then delete
 */
ALTER TABLE public."InvoiceLine" DROP CONSTRAINT "FK_InvoiceLineInvoiceId";
ALTER TABLE public."InvoiceLine" ADD CONSTRAINT "FK_InvoiceLineInvoiceId" FOREIGN KEY ("InvoiceId") REFERENCES "Invoice"("InvoiceId") on delete cascade;
ALTER TABLE public."Invoice" DROP CONSTRAINT "FK_InvoiceCustomerId";
ALTER TABLE public."Invoice" ADD CONSTRAINT "FK_InvoiceCustomerId" FOREIGN KEY ("CustomerId") REFERENCES "Customer"("CustomerId") on delete cascade;
delete from "Customer" where "FirstName" = 'Robert' and "LastName" = 'Walter'

/*3.1 System Defined Functions*/
--Create a query that returns the current time.
select CURRENT_TIMESTAMP

--Create a query that returns the length of name in MEDIATYPE table
select length(mt."Name") from "MediaType" mt 

/*3.2 System Defined Aggregate Functions*/
--Create a function that returns the average total of all invoices 
select avg(i."Total" ) from "Invoice" i 

--Create a function that returns the most expensive track
select max(t."UnitPrice" ) from "Track" t 

/*3.3 User Defined Functions*/
--Create a function that returns the average price of invoiceline items in the invoiceline table.
drop function average_invoiceline_price();

create or replace function average_invoiceline_price()
returns float as $$
begin
	return sum("UnitPrice") / count("UnitPrice") from "InvoiceLine";
end;
$$ language plpgsql;

--Create a function that returns all employees who are born after 1968.
drop function old_employees_lol();

create or replace function old_employees_lol()
returns setof timestamp as $$
begin
	return query select "BirthDate" from "Employee" where "BirthDate" < '1968-01-1 00:00:00';
end;
$$ language plpgsql;


/*4.1 After Insert Trigger*/
/* Create an after insert trigger on the employee table fired after a
 * new record is inserted into the table to set the phone number to 867-5309.*/
create or replace function set_employee_pn()
returns trigger as $$
begin
	if(TG_OP = 'INSERT') then
	new."Phone" = '867-5309';
	end if; 
	return new;
	end;
$$ language plpgsql;

create trigger employee_insert
before insert on "Employee"
for each row
execute function set_employee_pn();

/*4.2 Before Insert Trigger*/
-- Create a before trigger on the customer table that fires before a row is inserted from the table to set the company to Revature.*/
create or replace function cus_insert()
returns trigger as $$
begin
	if(TG_OP = 'INSERT') then
	new."Company" = 'Revature';
	end if; 
	return new;
	end;
$$ language plpgsql;

create trigger customer_insert
before insert on "Customer"
for each row
execute function cus_insert();

/*5.1 Inner Join*/
--Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
select c."FirstName", c."LastName", i."InvoiceId" from "Customer" c inner join "Invoice" i 
on c."CustomerId" = i."CustomerId" 

/*5.2 Full Outer Join*/
--Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
select c."CustomerId", c."FirstName", c."LastName", i."InvoiceId", i."Total" from "Customer" c full outer join "Invoice" i 
on c."CustomerId" = i."InvoiceId" 

/*5.3 Right Join*/
--Create a right join that joins album and artist specifying artist name and title.
-- Added some titles to the columns for fun :)
select a3."Name" as Artist, a2."Title" as album_title from "Album" a2 right join "Artist" a3 
on a3."ArtistId" = a2."ArtistId" 

/*5.4 Cross Join*/
--Create a cross join that joins album and artist and sorts by artist name in ascending order.
select * from "Album" a2 cross join "Artist" a3 order by a3."Name" 

/*5.5 Self Join*/
--Perform a self-join on the employee table, joining on the reportsto column.
select e."LastName" , e."FirstName", e2."LastName" as bossname from "Employee" e
join "Employee" e2 
on e."ReportsTo" = e2."EmployeeId"

/*6.1 Union*/
--Create a UNION query for finding the unique records of last name, first name, and phone number for all customers and employees.
select c."LastName", c."FirstName", c."Phone" from "Customer" c 
union
select e."LastName", e."FirstName", e."Phone" from "Employee" e 

/*6.2 Except All*/
--Create an EXCEPT ALL query for finding the all records of the city, state, and postal codes for all customers and all records of employees that have a different  city, state, and postal codes of any customer.
select c."City", c."State", c."PostalCode" from "Customer" c 
except all
select e."City", e."State", e."PostalCode" from "Employee" e 
