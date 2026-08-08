import {
    BarChart,
    Bar,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    ResponsiveContainer
} from 'recharts';

function ExpenseCharts({ dashboard }) {

    const categoryData = dashboard.monthlyCategoryTotals.reduce(
        (result, item) => {

            const existingCategory = result.find(
                entry => entry.category === item.category
            );

            if (existingCategory) {

                existingCategory.totalAmount += item.totalAmount;

            } else {

                result.push({
                    category: item.category,
                    totalAmount: item.totalAmount
                });
            }

            return result;

        },
        []
    );

    return (
        <>

            <div className="dashboard-section">

                <h2>Spending by Category</h2>

                <div className="chart-container">

                    <ResponsiveContainer
                        width="100%"
                        height={350}
                    >
                        <BarChart data={categoryData}>

                            <CartesianGrid strokeDasharray="3 3" />

                            <XAxis dataKey="category" />

                            <YAxis />

                            <Tooltip
                                formatter={(value) =>
                                    `₹${Number(value).toFixed(2)}`
                                }
                            />

                            <Bar
                                dataKey="totalAmount"
                                name="Total Spending"
                            />

                        </BarChart>
                    </ResponsiveContainer>

                </div>

            </div>

            <div className="dashboard-section">

                <h2>Top 5 Vendors by Spending</h2>

                <div className="chart-container">

                    <ResponsiveContainer
                        width="100%"
                        height={350}
                    >
                        <BarChart
                            data={dashboard.topVendors}
                            layout="vertical"
                        >

                            <CartesianGrid strokeDasharray="3 3" />

                            <XAxis type="number" />

                            <YAxis
                                type="category"
                                dataKey="vendorName"
                                width={100}
                            />

                            <Tooltip
                                formatter={(value) =>
                                    `₹${Number(value).toFixed(2)}`
                                }
                            />

                            <Bar
                                dataKey="totalAmount"
                                name="Total Spending"
                            />

                        </BarChart>

                    </ResponsiveContainer>

                </div>

            </div>

        </>
    );
}

export default ExpenseCharts;