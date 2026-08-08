import { useEffect, useState } from 'react';
import api from '../services/api';
import ExpenseForm from '../components/ExpenseForm';
import CsvUpload from '../components/CsvUpload';
import ExpenseCharts from '../components/ExpenseCharts';
import './Dashboard.css';

function Dashboard() {

    const [dashboard, setDashboard] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    const loadDashboard = async () => {

        try {

            setError('');

            const response = await api.get('/dashboard');

            setDashboard(response.data);

        } catch (error) {

            console.error('Failed to fetch dashboard:', error);

            setError('Failed to load dashboard data.');

        } finally {

            setLoading(false);
        }
    };

    useEffect(() => {
        loadDashboard();
    }, []);

    if (loading) {
        return (
            <div className="dashboard-state">
                <h2>Loading dashboard...</h2>
                <p>Please wait while we fetch your expense data.</p>
            </div>
        );
    }

    if (error) {
        return (
            <div className="dashboard-state error-state">
                <h2>Unable to load dashboard</h2>
                <p>{error}</p>

                <button
                    className="primary-button"
                    onClick={loadDashboard}
                >
                    Try Again
                </button>
            </div>
        );
    }

    const totalSpending =
        dashboard.monthlyCategoryTotals.reduce(
            (total, item) => total + item.totalAmount,
            0
        );

    const vendorCount =
        dashboard.topVendors.length;

    const categoryCount =
        new Set(
            dashboard.monthlyCategoryTotals.map(
                item => item.category
            )
        ).size;

    return (
        <div className="dashboard">

            {/* Header */}

            <div className="dashboard-header">
                <h1>Expense Manager</h1>
                <p>Track and analyze your expenses</p>
            </div>


            {/* Add Expense */}

            <ExpenseForm
                onExpenseAdded={loadDashboard}
            />


            {/* CSV Upload */}

            <CsvUpload
                onUploadComplete={loadDashboard}
            />


            {/* Summary Cards */}

            <div className="summary-grid">

                <div className="summary-card">
                    <h3>Total Spending</h3>
                    <p>
                        ₹{totalSpending.toFixed(2)}
                    </p>
                </div>

                <div className="summary-card">
                    <h3>Top Vendors</h3>
                    <p>
                        {vendorCount}
                    </p>
                </div>

                <div className="summary-card">
                    <h3>Categories</h3>
                    <p>
                        {categoryCount}
                    </p>
                </div>

                <div className="summary-card">
                    <h3>Anomalies</h3>
                    <p>
                        {dashboard.anomalyCount.count}
                    </p>
                </div>

            </div>


            {/* Charts */}

            <ExpenseCharts
                dashboard={dashboard}
            />


            {/* Top Vendors */}

            <div className="dashboard-section">

                <h2>Top 5 Vendors</h2>

                {dashboard.topVendors.length === 0 ? (

                    <p>No vendor data available yet.</p>

                ) : (

                    <table className="dashboard-table">

                        <thead>
                            <tr>
                                <th>Vendor</th>
                                <th>Total Spending</th>
                            </tr>
                        </thead>

                        <tbody>

                            {dashboard.topVendors.map(
                                vendor => (

                                    <tr
                                        key={vendor.vendorName}
                                    >
                                        <td>
                                            {vendor.vendorName}
                                        </td>

                                        <td>
                                            ₹
                                            {vendor.totalAmount.toFixed(
                                                2
                                            )}
                                        </td>
                                    </tr>

                                )
                            )}

                        </tbody>

                    </table>

                )}

            </div>


            {/* Monthly Category Spending */}

            <div className="dashboard-section">

                <h2>
                    Monthly Category Spending
                </h2>

                {dashboard.monthlyCategoryTotals.length === 0 ? (

                    <p>
                        No expense data available yet.
                    </p>

                ) : (

                    <table className="dashboard-table">

                        <thead>
                            <tr>
                                <th>Month</th>
                                <th>Category</th>
                                <th>Total Spending</th>
                            </tr>
                        </thead>

                        <tbody>

                            {dashboard.monthlyCategoryTotals.map(
                                (item, index) => (

                                    <tr key={index}>

                                        <td>
                                            {item.month}
                                        </td>

                                        <td>
                                            {item.category}
                                        </td>

                                        <td>
                                            ₹
                                            {item.totalAmount.toFixed(
                                                2
                                            )}
                                        </td>

                                    </tr>

                                )
                            )}

                        </tbody>

                    </table>

                )}

            </div>


            {/* Anomalies */}

            <div className="dashboard-section">

                <h2>
                    Anomalous Expenses
                </h2>

                {dashboard.anomalies.length === 0 ? (

                    <p>
                        No anomalous expenses found.
                    </p>

                ) : (

                    <table className="dashboard-table">

                        <thead>

                            <tr>
                                <th>Date</th>
                                <th>Vendor</th>
                                <th>Category</th>
                                <th>Amount</th>
                                <th>Status</th>
                            </tr>

                        </thead>

                        <tbody>

                            {dashboard.anomalies.map(
                                anomaly => (

                                    <tr
                                        key={anomaly.id}
                                        className="anomaly-row"
                                    >

                                        <td>
                                            {anomaly.date}
                                        </td>

                                        <td>
                                            {anomaly.vendorName}
                                        </td>

                                        <td>
                                            {anomaly.category}
                                        </td>

                                        <td>
                                            ₹
                                            {anomaly.amount.toFixed(
                                                2
                                            )}
                                        </td>

                                        <td>

                                            <span className="anomaly-badge">
                                                Anomaly
                                            </span>

                                        </td>

                                    </tr>

                                )
                            )}

                        </tbody>

                    </table>

                )}

            </div>

        </div>
    );
}

export default Dashboard;