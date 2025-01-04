insert into address (id, address_line1, address_line2, city, postal_code)
            values (1, 'ul. Wielosci Narodowych', '9/11', 'Adamczychy', '62-030');
insert into address (id, address_line1, address_line2, city, postal_code)
            values (2,'ul. Tadeusza Kościuszki', '19/2', 'Breslau', '12-123');

insert into doctor (first_name, last_name, telephone_number, email, doctor_number, specialization)
values
    ('John', 'Smith', '111-222-333', 'JohnSmith@example.com', '2af3fas2', 'GP'),
    ('Andrew', 'Andrzejewski', '222-333-444', 'AndrewAndrzejewski@example.com', '3afz3fa3', 'OCULIST');


insert into doctor_to_address (doctor_id, address_id)
values
    (1, 2),
    (2, 1);

insert into patient (first_name, last_name, telephone_number, email, patient_number, date_of_birth, gender)
values
    ('Alice', 'Williams', '555-1111', 'alice.williams@example.com', 'PN12345', '1990-01-15', 'FEMALE'),
    ('Bob', 'Johnson', '555-2222', 'bob.johnson@example.com', 'PN12346', '1985-02-20', 'MALE'),
    ('Charlie', 'Davis', '555-3333', 'charlie.davis@example.com', 'PN12347', '1992-03-25', 'MALE'),
    ('David', 'Miller', '555-4444', 'david.miller@example.com', 'PN12348', '1991-04-30', 'MALE'),
    ('Eva', 'Garcia', '555-5555', 'eva.garcia@example.com', 'PN12349', '1988-05-10', 'FEMALE');

insert into patient_to_address (patient_id, address_id)
values
    (1, 1),
    (2, 2),
    (3, 1),
    (4, 2),
    (5, 1);

insert into visit (description, time, patient_id, doctor_id)
VALUES
    ('Regular check-up', '2024-12-01 10:00:00', 1, 1),
    ('Blood test', '2024-12-05 14:00:00', 1, 1),
    ('Eye exam', '2024-12-10 09:00:00', 2, 2),
    ('Consultation for back pain', '2024-12-12 15:30:00', 2, 2),
    ('Skin check', '2024-12-15 11:00:00', 3, 2);

insert into medical_treatment (description, type, visit_id)
values
    ('Ultrasound scan', 'USG', 1),
    ('ECG', 'EKG', 2),
    ('X-ray', 'RTG', 3),
    ('Ultrasound scan', 'USG', 4),
    ('X-ray', 'RTG', 5);