import {OrderItem} from "./order-item";

export interface Order{
  id: string;
  address: string;
  wishList: OrderItem[];
}
