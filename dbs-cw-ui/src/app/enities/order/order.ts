import {OrderItem} from "./order-item";

export interface Order{
  address: string;
  wishList: OrderItem[];
}
