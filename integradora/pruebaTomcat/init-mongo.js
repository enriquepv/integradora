db = db.getSiblingDB('prueba');
db.createUser({
    user: 'root',
    pwd: 'alumno',
    roles: [{ role: 'readWrite', db: 'prueba' }]
});
