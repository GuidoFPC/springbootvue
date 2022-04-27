import axios from 'axios';

const docsapi = axios.create({
    baseURL: 'http://localhost:8180/documentos'
});

export default docsapi;