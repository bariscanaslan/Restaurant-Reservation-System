import React from "react";
import { Outlet } from "react-router-dom";
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';


const Index = () => {

    const navigate = useNavigate();

    useEffect(() => {
        if (window.location.pathname === '/index') {
            navigate('/', { replace: true });
        }

        const styles = [
            "/Libs/css/Promotion/Home/Home.css",
            "/Libs/css/Promotion/Footer.css",
            "/Libs/css/Promotion/Header.css"
        ];

        const styleElements = styles.map(href => {
            const link = document.createElement("link");
            link.rel = "stylesheet";
            link.href = href;
            document.head.appendChild(link);
            return link;
        });

        return () => {
            styleElements.forEach(link => document.head.removeChild(link));
        };

    }, [navigate]);

    return (
        <>
            <Outlet></Outlet>
        </>
    );
};

export default Index;