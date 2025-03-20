INSERT INTO users (username, password, full_name)
VALUES ('admin', '$2a$12$5AawqsDIn6UpIFsCIZW0MuE0kPFpaEmEWMI72.XfmHUluoZ6zvoEu', 'Admin')
ON DUPLICATE KEY UPDATE id=id; -- Nếu đã tồn tại, không làm gì

-- Lấy ID của admin
SET @admin_id = (SELECT id FROM users WHERE username = 'admin');

-- Chèn các quyền nếu chưa tồn tại
INSERT INTO user_roles (user_id, role)
SELECT @admin_id, 'ADMIN' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM user_roles WHERE user_id = @admin_id AND role = 'ADMIN')
UNION
SELECT @admin_id, 'CUSTOMER' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM user_roles WHERE user_id = @admin_id AND role = 'CUSTOMER')
UNION
SELECT @admin_id, 'MANAGER' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM user_roles WHERE user_id = @admin_id AND role = 'MANAGER');
