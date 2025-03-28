db = db.getSiblingDB("db_test");

// Tạo user thường
db.createUser({
    user: "udh",
    pwd: "udh",
    roles: [{ role: "readWrite", db: "db_test" }]
});
