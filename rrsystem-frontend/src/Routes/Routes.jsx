import { createBrowserRouter } from 'react-router-dom';
import Index from '../Layouts/Promotion/Index.jsx';
import Home from "../Pages/Promotion/Home/Home.jsx";
import Pricing from "../Pages/Promotion/Pricing/Pricing.jsx";
import Contact from '../Pages/Promotion/Contact/Contact.jsx';
import Features from '../Pages/Promotion/Features/Features.jsx';
import Admin from '../Layouts/Dashboard/Admin/Admin.jsx';

const router = createBrowserRouter([
    {
        path: '/',
        element: <Index />,
        children: [
            {
                path: "/",
                element: <Home />
            },
            {
                path: "home",
                element: <Home />
            },
            {
                path: "pricing",
                element: <Pricing />
            },
            {
                path: "contact",
                element: <Contact />
            },
            {
                path: "features",
                element: <Features />
            },
            {
                path: "admin",
                element: <Admin />
            }
        ]
    },
    {
        path: '/index',
        element: <Index />
    }
]);

export default router;