const balanceElement = document.getElementById('balance');
const transactionForm = document.getElementById('transaction-form');
const transactionHistory = document.getElementById('transaction-history');

let transactions = [];

// Load transactions from localStorage
function loadTransactions() {
    const stored = localStorage.getItem('expenseTrackerTransactions');
    if (stored) {
        transactions = JSON.parse(stored);
    } else {
        transactions = [];
    }
}

// Save transactions to localStorage
function saveTransactions() {
    localStorage.setItem('expenseTrackerTransactions', JSON.stringify(transactions));
}

// Update balance display
function updateBalance() {
    let balance = 0;
    transactions.forEach(tx => {
        balance += tx.type === 'income' ? tx.amount : -tx.amount;
    });
    balanceElement.textContent = balance.toFixed(2);
}

// Render transaction history
function renderHistory() {
    transactionHistory.innerHTML = '';
    transactions.forEach((tx, idx) => {
        const li = document.createElement('li');
        li.className = tx.type;
        li.innerHTML = `<strong>${tx.type === 'income' ? '+' : '-'}${tx.amount.toFixed(2)}</strong> - ${tx.description}`;
        transactionHistory.appendChild(li);
    });
}

// Add transaction
transactionForm.addEventListener('submit', function(e) {
    e.preventDefault();
    const type = transactionForm.type.value;
    const description = transactionForm.description.value.trim();
    const amount = parseFloat(transactionForm.amount.value);
    if (!description || isNaN(amount) || amount <= 0) {
        alert('Please enter a valid description and amount.');
        return;
    }
    transactions.push({ type, description, amount });
    saveTransactions();
    updateBalance();
    renderHistory();
    transactionForm.reset();
});

// Initial load
loadTransactions();
updateBalance();
renderHistory();