import {Component} from '@angular/core';
import {Article} from "./enities/article/Article";
import {HttpErrorResponse} from "@angular/common/http";
import {ArticleService} from "./enities/article/article.service";
import {ReadyItem} from "./enities/readyItem/ready-item";
import {WaitingItem} from "./enities/waitingItem/waiting-item";
import {ReadyItemService} from "./enities/readyItem/ready-item.service";
import {WaitingItemService} from "./enities/waitingItem/waiting-item.service";
import {Element} from "@angular/compiler";
import {FormBuilder, FormGroup, NgForm} from "@angular/forms";
import {OrderItem} from "./enities/order/order-item";
import {OrderService} from "./enities/order/order.service";
import {InvoiceService} from "./enities/invoice/invoice.service";
import {Order} from "./enities/order/order";
import {Invoice} from "./enities/invoice/invoice";
import {WorkerService} from "./enities/worker/worker.service";
import {error} from "@angular/compiler-cli/src/transformers/util";

// import {addDocumentEL} from "../assets/js/script"

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'dbs-cw-ui';
  public articles: Article[] | undefined;
  public readyItems: ReadyItem[] | undefined;
  public waitingItems: WaitingItem[] | undefined;
  public orders: Order[] | undefined;
  public wishList: OrderItem[];
  public invoices: Invoice[];
  public role: boolean | undefined;
  public wishListAddition: boolean = false;

  constructor(private articleService: ArticleService,
              private readyItemService: ReadyItemService,
              private waitingItemService: WaitingItemService,
              private orderService: OrderService,
              private invoiceService: InvoiceService,
              private workerService: WorkerService) {
    this.wishList = []
    this.invoices = []
  }

  ngOnInit() {
    this.getArticles();
    this.getReadyItems();
    this.getWaitingItems();
    // @ts-ignore
    document.addEventListener('click', this.EventHandler.bind(this));
    this.PopUp("login-form");
  }

  public getArticles(): void {
    this.articleService.getArticles().subscribe(
      (response: Article[]) => {
        this.articles = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public addArticle(form: NgForm): void {
    this.articleService.addArticle(form.value).subscribe(
      (response: Article) => {
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public editArticle(form: NgForm): void {
    this.articleService.updateArticle(form.value).subscribe(
      (response: Article) => {
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public deleteArticle(form: NgForm): void {
    this.articleService.deleteArticle(form.value.id).subscribe((response: void) => {
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      })
  }

  public getReadyItems(): void {
    this.readyItemService.getReadyItems().subscribe(
      (response: ReadyItem[]) => {
        this.readyItems = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public addReadyItem(form: NgForm): void {
    this.readyItemService.addReadyItem(form.value).subscribe(
      (response: ReadyItem) => {
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public editReadyItem(form: NgForm): void {
    this.readyItemService.updateReadyItem(form.value).subscribe(
      (response: ReadyItem) => {
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public deleteReadyItem(form: NgForm): void {
    this.readyItemService.deleteReadyItem(form.value.id).subscribe((response: void) => {
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      })
  }

  public getWaitingItems(): void {
    this.waitingItemService.getWaitingItems().subscribe(
      (response: WaitingItem[]) => {
        this.waitingItems = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public addWaitingItem(form: NgForm): void {
    this.waitingItemService.addWaitingItem(form.value).subscribe(
      (response: WaitingItem) => {
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public editWaitingItem(form: NgForm): void {
    this.waitingItemService.updateWaitingItem(form.value).subscribe(
      (response: WaitingItem) => {
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public deleteWaitingItem(form: NgForm): void {
    this.waitingItemService.deleteWaitingItem(form.value.id).subscribe((response: void) => {
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      })
  }

  public getOrders(): void {
    this.orderService.getOrders().subscribe(
      (response: Order[]) => {
        this.orders = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public addOrder(form: NgForm): void {
    this.orderService.addOrder(form.value).subscribe(
      (response: Order) => {
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getInvoices(): void {
    // if (this.orders != undefined) {
    //   for (const order of this.orders) {
    //
    //   }
    // }
    this.invoiceService.getInvoices().subscribe(
      (response: Invoice[]) => {
        this.invoices = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public addInvoice(form: NgForm): void {
    this.invoiceService.addInvoice(form.value.id).subscribe(
      (response: Invoice) => {
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public approveInvoice(form: NgForm): void {
    this.invoiceService.approveInvoice(form.value.id).subscribe(
      (response: Invoice) => {
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getWorkerStatus(form: NgForm): void {
    this.workerService.getWorkerStatus(form.value).subscribe(
      (response) => {
        if (response.role == "MANAGER") {
          this.role = true;
        } else {
          this.role = false;
        }
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }

  public tabFill(tabContentID: string) {
    if (document.getElementsByClassName("table-container")[0].classList.contains("table-container-hidden")) {
      document.getElementsByClassName("table-container-hidden")[0].classList.remove("table-container-hidden");
    }
    this.SelectTab(tabContentID);
    // const tabsContainer = document.querySelector('.tabs-container');
    const table = document.querySelector('.table-container table table');
    // @ts-ignore
    // tabsContainer.querySelectorAll('.tabs-buttons li').forEach(item => item.classList.remove('active'));
    // @ts-ignore
    table.innerHTML = '';
    // angular.
    switch (tabContentID) {
      case "articles" : {
        this.getArticles();
        window.setTimeout(() => {
            const headers = document.querySelector('.headers');
            // @ts-ignore
            headers.innerHTML = '';
            if (this.articles != undefined) {
              const keys = Object.keys(this.articles[0])
              for (let i = 0; i < keys.length; i++) {
                const columnName = document.createElement('TD');
                columnName.appendChild(document.createTextNode(keys[i]));
                columnName.classList.add('column-name');
                // @ts-ignore
                headers.appendChild(columnName)
              }
              // @ts-ignore
              headers.classList.add('headers');
            }
            // @ts-ignore
            for (const article of this.articles) {
              const tr = document.createElement("tr");
              const keys = Object.keys(article);
              for (let i = 0; i < keys.length; i++) {
                tr.appendChild(document.createElement('td'));
              }
              tr?.cells[0].appendChild(document.createTextNode(article.id));
              tr?.cells[1].appendChild(document.createTextNode(article.productNameUA));
              tr?.cells[2].appendChild(document.createTextNode(article.productNameEN));
              tr?.cells[3].appendChild(document.createTextNode(article.supplierName));
              tr?.cells[4].appendChild(document.createTextNode(article.expirationTerm));
              tr?.cells[5].appendChild(document.createTextNode(String(article.disposalNeeded)));
              table?.append(tr);
            }
            table?.classList.add('table');
          }
          , 100)
        break;
      }
      case "readyItems": {
        this.getReadyItems();
        window.setTimeout(() => {
            const headers = document.querySelector('.headers');
            // @ts-ignore
            headers.innerHTML = '';
            if (this.readyItems != undefined) {
              const keys = Object.keys(this.readyItems[0])
              for (let i = 0; i < keys.length; i++) {
                const columnName = document.createElement('TD');
                columnName.appendChild(document.createTextNode(keys[i]));
                columnName.classList.add('column-name');
                // @ts-ignore
                headers.appendChild(columnName)
              }
              // @ts-ignore
              headers.classList.add('headers');
            }
            // @ts-ignore
            for (const readyItem of this.readyItems) {
              const tr = document.createElement("tr");
              tr.setAttribute("id", readyItem.id);
              const keys = Object.keys(readyItem);
              for (let i = 0; i < keys.length; i++) {
                tr.appendChild(document.createElement('td'));
              }
              tr?.cells[0].appendChild(document.createTextNode(readyItem.id));
              tr?.cells[1].appendChild(document.createTextNode(String(readyItem.totalItemAmount)));
              tr?.cells[2].appendChild(document.createTextNode(String(readyItem.reservedItemAmount)));
              tr?.cells[3].appendChild(document.createTextNode(String(readyItem.availableItemAmount)));
              tr?.cells[4].appendChild(document.createTextNode(readyItem.storagePlace));
              tr?.cells[5].appendChild(document.createTextNode(String(readyItem.price)));
              tr?.cells[6].appendChild(document.createTextNode(readyItem.measureUnit));
              table?.append(tr);
            }
            table?.classList.add('table');
          }
          , 100)
        break;
      }
      case "waitingItems": {
        this.getWaitingItems();
        window.setTimeout(() => {
            const headers = document.querySelector('.headers');
            // @ts-ignore
            headers.innerHTML = '';
            if (this.waitingItems != undefined) {
              const keys = Object.keys(this.waitingItems[0])
              for (let i = 0; i < keys.length; i++) {
                const columnName = document.createElement('TD');
                columnName.appendChild(document.createTextNode(keys[i]));
                columnName.classList.add('column-name');
                // @ts-ignore
                headers.appendChild(columnName)
              }
              // @ts-ignore
              headers.classList.add('headers');
            }
            // @ts-ignore
            for (const waitingItem of this.waitingItems) {
              const tr = document.createElement("tr");
              const keys = Object.keys(waitingItem);
              for (let i = 0; i < keys.length; i++) {
                tr.appendChild(document.createElement('td'));
              }
              tr?.cells[0].appendChild(document.createTextNode(waitingItem.id));
              tr?.cells[1].appendChild(document.createTextNode(String(waitingItem.totalItemAmount)));
              tr?.cells[2].appendChild(document.createTextNode(String(waitingItem.price)));
              tr?.cells[3].appendChild(document.createTextNode(waitingItem.measureUnit));
              table?.append(tr);
            }
            table?.classList.add('table');
          }
          , 100)
        break;
      }
      case "orders": {
        this.getOrders();
        window.setTimeout(() => {
            const headers = document.querySelector('.headers');
            // @ts-ignore
            headers.innerHTML = '';
            if (this.orders != undefined && this.orders[0] != null) {
              const keys = Object.keys(this.orders[0])
              for (let i = 0; i < keys.length; i++) {
                const columnName = document.createElement('TD');
                columnName.appendChild(document.createTextNode(keys[i]));
                columnName.classList.add('column-name');
                // @ts-ignore
                headers.appendChild(columnName)
              }
              // @ts-ignore
              headers.classList.add('headers');
              for (const order of this.orders) {
                const tr = document.createElement("tr");
                const keys = Object.keys(order);
                for (let i = 0; i < keys.length; i++) {
                  tr.appendChild(document.createElement('td'));
                }
                tr?.cells[0].appendChild(document.createTextNode(String(order.id)));
                tr?.cells[1].appendChild(document.createTextNode(String(order.address)));
                tr?.cells[2].appendChild(document.createTextNode(String(order.wishList)));
                table?.append(tr);
              }
            }
            table?.classList.add('table');
          }
          , 100)
        break;
      }
      case "invoices": {
        this.getInvoices();
        const headers = document.querySelector('.headers');
        // @ts-ignore
        headers.innerHTML = '';
        window.setTimeout(() => {
            if (this.invoices != undefined && this.invoices.length > 0) {
              const keys = Object.keys(this.invoices[0])
              for (let i = 0; i < keys.length; i++) {
                const columnName = document.createElement('TD');
                columnName.appendChild(document.createTextNode(keys[i]));
                columnName.classList.add('column-name');
                // @ts-ignore
                headers.appendChild(columnName)
              }
              // @ts-ignore
              headers.classList.add('headers');

              for (const invoice of this.invoices) {
                const tr = document.createElement("tr");
                const keys = Object.keys(this.invoices);
                for (let i = 0; i < keys.length; i++) {
                  tr.appendChild(document.createElement('td'));
                }
                tr?.cells[0].appendChild(document.createTextNode(invoice.id));
                tr?.cells[1].appendChild(document.createTextNode(String(invoice.approved)));
                tr?.cells[2].appendChild(document.createTextNode(String(invoice.items)));
                tr?.cells[3].appendChild(document.createTextNode(String(invoice.totalSum)));
                table?.append(tr);
              }
            }
            table?.classList.add('table');
          }
          , 100)
        break;
      }
    }
  }

  public EventHandler(e: Event & { target: HTMLInputElement }): void {
    if (e.target.classList.contains("black-screen")) {
      this.BlackScreen();
    }
    if (this.wishListAddition) {
      // @ts-ignore
      if (e.target.parentNode.tagName == 'TR') {
        const tabName = document.querySelector('.active-tab')?.innerHTML;
        if (tabName == "ReadyItem"){this.SelectTableRow(<HTMLElement>e.target.parentNode);
          // @ts-ignore
          let wishId = e.target.parentNode.id;
          this.OrderPopup(wishId);}
      }
    }
    if (e.target.classList.contains("control-button")) {
      this.SelectControlButton(e.target)
    }
    console.log(e.target);
  }

  public toggleWishListAddition() {
    this.wishListAddition = !this.wishListAddition;
  }

  public OrderPopup(wishId: string) {
    let wishAmount = prompt("Enter amount", "");
    if (Number(wishAmount) > 0){let item;
      item = {"id": wishId, "value": Number(wishAmount)};
      this.wishList?.push(<OrderItem><unknown>item);
    }
  }

  public SelectControlButton(button: HTMLElement) {
    const activeCButtons = document.querySelectorAll(".active-control-button");
    // @ts-ignore
    for (const cButton of activeCButtons) {
      cButton.classList.remove("active-control-button");
    }
    button.classList.add("active-control-button");
  }

  public SelectTableRow(row: HTMLElement) {
    const activeRows = document.querySelectorAll(".active-row");
    // @ts-ignore
    for (const activeRow of activeRows) {
      activeRow.classList.remove("active-row");
    }
    row.classList.add("active-row");
  }

  public SelectTab(id: string) {
    const activeTab = document.querySelectorAll(".active-tab");
    // @ts-ignore
    for (const activeTabElement of activeTab) {
      activeTabElement.classList.remove('active-tab');
    }
    // @ts-ignore
    document.getElementById(id).classList.add('active-tab');
  }

  public PopUp(mode: string) {
    const container = document.getElementsByClassName("pop-up-container")[0];
    const button = document.querySelector('.active-control-button');
    if (container.classList.contains("pop-up-container-hidden")) {
      container.classList.remove("pop-up-container-hidden");
      this.BlackScreen()

    } else {
      button?.classList.remove('active-control-button');
      container.classList.add("pop-up-container-hidden");
      if (document.getElementsByClassName('form-visible').length > 0) {
        document.getElementsByClassName('form-visible')[0].classList.remove('form-visible');
      }
    }
    const columns = document.querySelectorAll('.column-name');
    const row = document.querySelector('.active-row')
    const tableName = document.querySelector('.active-tab')?.innerHTML.toLowerCase();
    let i = 0;
    let input;
    // @ts-ignore
    for (const column of columns) {
      if (mode != "delete") {
        input = document.getElementById(tableName + `_${column.innerHTML}`);
        // @ts-ignore
        if (row != null && input != null) {
          // @ts-ignore
          input.value = String(row.children[i].innerHTML);
        }
        i++;
      }
    }

    switch (mode) {
      case "add": {
        const form = document.getElementById("add_" + tableName + "_form");
        const rowVals = document.querySelector(".active-row")?.children;
        form?.classList.add('form-visible');
        const inputs = form?.querySelectorAll('input');
        // @ts-ignore
        if (rowVals != undefined) {
          for (let j = 0; j < rowVals.length; j++) {
            // @ts-ignore
            inputs[j].value = rowVals[j].innerHTML;
          }
        }
        break;
      }
      case "edit": {
        const form = document.getElementById("add_" + tableName + "_form");
        const rowVals = document.querySelector(".active-row")?.children;
        form?.classList.add('form-visible');
        const inputs = form?.querySelectorAll('input');
        // @ts-ignore
        if (rowVals != undefined) {
          for (let j = 0; j < rowVals.length; j++) {
            // @ts-ignore
            inputs[j].value = rowVals[j].innerHTML;
          }
        }
        break;
      }
      case "delete": {
        const form = document.getElementById("del_form");
        form?.classList.add('form-visible');
        const rowVals = document.querySelector(".active-row")?.children;
        const inputs = form?.querySelectorAll('input');
        if (rowVals != undefined) {
          // @ts-ignore
          inputs[0].value = rowVals[0].innerHTML;
        }

        break;
      }
      case "order": {
        const form = document.getElementById("add_order_form");
        form?.classList.add('form-visible');
        break;
      }
      case "create-invoice": {
        const form = document.getElementById("invoice_form");
        form?.classList.add('form-visible');
        const rowVals = document.querySelector(".active-row")?.children;
        const inputs = form?.querySelectorAll('input');


        break;
      }
      case "approve-invoice": {
        const form = document.getElementById("invoice_form");
        form?.classList.add('form-visible');
        const rowVals = document.querySelector(".active-row")?.children;
        const inputs = form?.querySelectorAll('input');
        if (rowVals != undefined && inputs != undefined) {
          // @ts-ignore
          inputs[0].value = rowVals[0].innerHTML;
        }

        break;
      }
      case "login-form": {
        const form = document.getElementById("login-form");
        form?.classList.add('form-visible');
        break;
      }

    }
  }

  public onSubmit(form: NgForm) {
    // @ts-ignore
    switch (document.querySelector('.active-tab').id) {
      case 'articles': {
        // @ts-ignore
        switch (document.querySelector('.active-control-button').innerHTML) {
          case 'Add': {
            this.addArticle(form);
            break;
          }
          case 'Edit': {
            this.editArticle(form);
            break;
          }
          case 'Delete': {
            this.deleteArticle(form);
            break;
          }
        }
        break;
      }
      case 'readyItems': {
        // @ts-ignore
        switch (document.querySelector('.active-control-button').innerHTML) {
          case 'Add': {
            this.addReadyItem(form);
            break;
          }
          case 'Edit': {
            this.editReadyItem(form);
            break;
          }
          case 'Delete': {
            this.deleteReadyItem(form);
            break;
          }
          case 'Order': {
            form.value.wishList = this.wishList;
            this.addOrder(form);
          }
        }
        break;
      }
      case 'waitingItems': {
        // @ts-ignore
        switch (document.querySelector('.active-control-button').innerHTML) {
          case 'Add': {
            this.addWaitingItem(form);
            break;
          }
          case 'Edit': {
            this.editWaitingItem(form);
            break;
          }
          case 'Delete': {
            this.deleteWaitingItem(form);
            break;
          }
        }
        break;
      }
      case 'orders': {
        // @ts-ignore
        switch (document.querySelector('.active-control-button').id) {
          case 'create-invoice': {
            this.addInvoice(form);
            break;
          }
        }
        break;
      }
      case 'invoices': {
        // @ts-ignore
        switch (document.querySelector('.active-control-button').id) {
          case 'approve-invoice': {
            this.approveInvoice(form);
            break;
          }
        }
      }
    }
  }

  public BlackScreen() {
    const blackScreen = document.getElementsByClassName("black-screen")[0];

    if (blackScreen.classList.contains("black-screen-hidden")) {
      blackScreen.classList.remove("black-screen-hidden")
    } else {
      blackScreen.classList.add("black-screen-hidden")
      this.PopUp("");
    }
  }


// Функція показує всі приховані елементи, якщо користувач увійшов до аккаунту
  public checkUser(form: NgForm) {
    this.getWorkerStatus(form);

    setTimeout(() => {
      const hidden_counter = document.getElementsByClassName("hidden-item").length;
      if (this.role == true) {
        for (let i = 0; i < hidden_counter; i++) {
          document.getElementsByClassName("hidden-item")[0].classList.remove("hidden-item");
        }
      } else if (this.role == false) {

      }
    }, 500)
  }

}





