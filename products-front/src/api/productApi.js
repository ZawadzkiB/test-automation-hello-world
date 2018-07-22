import axios from 'axios'
import {
  PRODUCT_BASE
} from '../api/paths'

export const HTTP = axios.create({
  baseURL: PRODUCT_BASE,
  timeout: 5000,
  headers: {
    // 'Access-Control-Allow-Origin':'http://localhost:8080'
    // Authorization: 'Bearer {token}'
  }
})
