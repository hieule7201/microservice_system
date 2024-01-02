import {addBook, deleteBook, getAllBook, getBookById, updateBook} from "@/api/book.js";

const getAllBookAction = async ({commit}, keyword) => {
    try{
    const data = await getAllBook(keyword)
    commit('setAllBook', data.data)

    }catch (e){
        console.log(e.error.response.message)
    }
}
const addBookAction = async ({commit}, book) => {
    try {
        const data = await addBook(book);
        commit('setMessageAPI', data.data)
    }catch (e){
        console.log(e.error.response.message)
    }
}
const detailsBookAction = async ({commit}, idBook) => {
    try {
        const data = await getBookById(idBook);
        commit('setDetailsBook', data.data)
    }catch (e){
        console.log(e.error.response.message)
    }
}
const updateBookAction = async ({commit}, book) => {
    try {
        const data = await updateBook(book);
        commit('setMessageAPI', data.data)
    }catch (e){
        console.log(e.error.response.message)
    }
}

const deleteBookAction = async ({commit}, idBook) => {
    try {
        const data = await deleteBook(idBook);
        commit('setMessageAPI', data.data)
    }catch (e){
        console.log(e.error.response.message)
    }
}

export {getAllBookAction, addBookAction, updateBookAction, detailsBookAction, deleteBookAction}