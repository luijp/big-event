import request from "@/utils/request.js";
import {useTokenStore} from "@/stores/token.js";

export const articleCategoryListService = () => {
    return request.get('/category');
}

export const articleCategoryAddService = (categoryData)=>{
    return request.post('/category',categoryData)
}