import axios from 'axios';

const ORDERS_REST_API_URL = 'http://localhost:8080/api/orders';


class OrderService{

    async post(order){ 
        await axios.post(ORDERS_REST_API_URL, order);
    }
}

export default new OrderService();