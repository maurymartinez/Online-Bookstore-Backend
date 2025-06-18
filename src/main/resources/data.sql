-- Insert user
INSERT INTO users (id, email, name, password)
VALUES ('00000000-0000-0000-0000-000000000001', 'user@example.com', 'User One',
        '$2a$10$e0MYzXyjpJS7Pd0RVvHwHeK.ICK4OQXsDyG8iE8HORSMD1rOai1vy');

-- Insert books
INSERT INTO books (id, title, author, price, copies)
VALUES ('11111111-1111-1111-1111-111111111101', 'Clean Code', 'Robert C. Martin', 39.99, 2),
       ('11111111-1111-1111-1111-111111111102', 'Effective Java', 'Joshua Bloch', 44.99, 4),
       ('11111111-1111-1111-1111-111111111103', 'Domain-Driven Design', 'Eric Evans', 54.95, 3),
       ('11111111-1111-1111-1111-111111111104', 'Spring in Action', 'Craig Walls', 42.50, 1),
       ('11111111-1111-1111-1111-111111111105', 'Refactoring', 'Martin Fowler', 49.95, 5),
       ('11111111-1111-1111-1111-111111111106', 'The Pragmatic Programmer', 'Andrew Hunt', 37.99, 3),
       ('11111111-1111-1111-1111-111111111107', 'Design Patterns', 'Gamma et al.', 59.99, 2),
       ('11111111-1111-1111-1111-111111111108', 'Test Driven Development', 'Kent Beck', 35.00, 4),
       ('11111111-1111-1111-1111-111111111109', 'Software Architecture', 'Len Bass', 46.00, 1),
       ('11111111-1111-1111-1111-111111111110', 'Microservices Patterns', 'Chris Richardson', 51.00, 2);
