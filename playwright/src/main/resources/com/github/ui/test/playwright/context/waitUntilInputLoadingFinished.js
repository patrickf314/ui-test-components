// noinspection BadExpressionStatementJS
/**
 * Waits for the given node to not have to attribute data-loading=true.
 *
 * @param {HTMLInputElement} node the node
 * @returns {Promise<void>} the promise resolving when the input is not loading
 */
node => new Promise((resolve, reject) => {
    const timeout = 10; //second

    let counter = timeout * 20;

    function checkLoading() {
        counter--;
        if (counter === 0) {
            reject(new Error(`Button is still disabled after ${timeout} seconds.`));
            return;
        }

        if (node.dataset.loading === "true") {
            setTimeout(checkLoading, 50);
        } else {
            resolve();
        }
    }

    checkLoading();
})