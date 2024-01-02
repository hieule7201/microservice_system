import instance from './instance.js'

const addBook = async (book)=>{
    return await instance.post('/book/add_book', book)
}

const updateBook = async (book) => {
    return await instance.put('/book/update_book/', book)
}

const getAllBook = async (keyword) => {
    return await instance.get('/book/get_book', {
        params: {
            keyword: keyword
        }
    });
}
const getBookById = async (idBook) => {
    return await  instance.get(`/book/find_book/${idBook}`);
}
const deleteBook = async (idBook) => {
    return await  instance.get(`/book/delete_book/${idBook}`);
}
export {addBook, updateBook, getAllBook, getBookById, deleteBook}